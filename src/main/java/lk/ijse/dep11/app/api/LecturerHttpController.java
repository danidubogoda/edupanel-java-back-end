package lk.ijse.dep11.app.api;

import lk.ijse.dep11.app.to.request.LecturerReqTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/lecturers")
@CrossOrigin
public class LecturerHttpController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public void createNewLecturer(@ModelAttribute @Valid LecturerReqTO lecturer){    //"requestBody" can't use with multipart form data
        System.out.println("createLecturers()");
        System.out.println(lecturer);
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
