package lk.ijse.dep11.app.api;

import lk.ijse.dep11.app.to.request.LecturerReqTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.*;

@RestController
@RequestMapping("/api/v1/lecturers")
@CrossOrigin
public class LecturerHttpController {

    @Autowired
    private DataSource pool;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public void createNewLecturer(@ModelAttribute @Valid LecturerReqTO lecturer){    //"requestBody" can't use with multipart form data
        System.out.println("createLecturers()");
        System.out.println(lecturer);


        try (Connection connection = pool.getConnection()) {
            connection.setAutoCommit(false);
            try{
            
            PreparedStatement stmInsertLecturer = connection.prepareStatement("INSERT INTO lecture " +
                    "(name, designation, qualifications, linkdin) " +
                    "VALUES (?,?,?,?)");
            stmInsertLecturer.setString(1, lecturer.getName());
            stmInsertLecturer.setString(2, lecturer.getDesignation());
            stmInsertLecturer.setString(3, lecturer.getQualifications());
            stmInsertLecturer.setString(4, lecturer.getLinkedin());
            stmInsertLecturer.executeUpdate();
            ResultSet generatedKeys = stmInsertLecturer.getGeneratedKeys();
            generatedKeys.next();
            int lecturerId = generatedKeys.getInt(1);
            String picture = lecturerId + "-" + lecturer.getName();

            if (lecturer.getPicture() != null || !lecturer.getPicture().isEmpty()) {
                PreparedStatement stmUpdateLecturer
                        = connection.prepareStatement("UPDATE lecture SET picture = ? WHERE id = ?");
                stmUpdateLecturer.setString(1, picture);
                stmUpdateLecturer.setInt(2, lecturerId);
                stmUpdateLecturer.executeUpdate();
            }

            final String table = lecturer.getType().equalsIgnoreCase("full-time")
                    ? "full_time_rank" : "part_time_rank";
                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery("SELECT `rank` FROM full_time_rank ORDER BY `rank` DESC LIMIT 1");
                int rank;
                if(!rst.next())rank =1;
                else rank = rst.getInt("rank") + 1;

                PreparedStatement stmInsertRank = connection
                        .prepareStatement("INSERT INTO full_time_rank(lecturer_id, `rank`) VALUES (?, ?)");
                stmInsertRank.setInt(1, lecturerId);
                stmInsertRank.setInt(2, rank);
                stmInsertRank.executeUpdate();

            connection.commit();

        }catch (Throwable t){
            connection.rollback();
            throw t;
        }finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
