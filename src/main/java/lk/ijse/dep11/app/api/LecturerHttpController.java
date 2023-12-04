package lk.ijse.dep11.app.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lecturers")
@CrossOrigin
public class LecturerHttpController {

    @PostMapping
    public void createNewLecturer(){
        System.out.println("createLecturers()");
    }


    @PatchMapping("/{lecturer-id}")
    public void updateLecturerDetails(){

        System.out.println("updateLecturers()");
    }

    @DeleteMapping("/{lecturer-id}")
    public void deleteLecturer(){

        System.out.println("deleteLecturers()");
    }


    @GetMapping
    public void getAllLecturers(){

        System.out.println("getAllLecturers()");
    }
}
