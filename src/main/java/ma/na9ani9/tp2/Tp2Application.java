package ma.na9ani9.tp2;

import ma.na9ani9.tp2.entities.Patient;
import ma.na9ani9.tp2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner {
@Autowired
private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========================={Ajouter Patient}==========================");
//        patientRepository.save(new Patient(null, "Marouane", new Date(),false, 75));
//        patientRepository.save(new Patient(null, "Adam", new Date(),true, -12));
//        patientRepository.save(new Patient(null, "Hatim", new Date(),false, 404));
//        patientRepository.save(new Patient(null, "Aya", new Date(),true, 0));
        for(int i=0; i<100; i++){
            patientRepository.save(new Patient(null, "Patient_"+i, new Date(), Math.random() > 0.5, (int)(Math.random()*100)));
        }
//        List<Patient> patients = patientRepository.findAll();
        // =================== Pagination=====================================
        System.out.println("========================={Afficher Liste Patients}==========================");

        Page<Patient> patientsByPage = patientRepository.findAll(PageRequest.of(1, 5));
        System.out.println("\n------------------------------------------\n");
        System.out.println("[*] Total Pages: "+patientsByPage.getTotalPages());
        System.out.println("[*] Total Elements: "+patientsByPage.getTotalElements());
        System.out.println("[*] Page Number: "+patientsByPage.getNumber());
        System.out.println("\n------------------------------------------\n");

        patientsByPage.forEach(p-> System.out.println(p.getId()+" "+p.getNom()+" "+p.getDateNaissance()+" "+p.getScore()+" "+p.isMalade()));
        System.out.println("========================={Modifier Patient}==========================");
        Patient patient = patientRepository.findById(1L).orElse(null);
        if(patient!=null){
            patient.setScore(300);
            patientRepository.save(patient);
            System.out.println(patient.getId()+" "+patient.getNom()+" "+patient.getDateNaissance()+" "+patient.getScore()+" "+patient.isMalade());
        }
        System.out.println("========================={Supprimer Patient}==========================");
        patientRepository.deleteById(2L);
        System.out.println("========================={Liste des Malades}==========================");
        List<Patient> byMalade1 = patientRepository.findByMalade(false);

        Page<Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(1, 5));
        byMalade.forEach(p-> System.out.println(p.getId()+" "+p.getNom()+" "+p.getDateNaissance()+" "+p.getScore()+" "+p.isMalade()));
    }
}
