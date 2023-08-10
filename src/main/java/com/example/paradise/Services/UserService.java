package com.example.paradise.Services;
import com.example.paradise.UserPojo.*;
import com.example.paradise.entity.Booking;
import com.example.paradise.entity.Feedback;
import com.example.paradise.entity.User;
import java.util.List;

public interface UserService {

    List<Booking> fetchAll();

    void sendEmail();

    String save(UserPojo userPojo);
    String update(UserPojo userPojo);

    Booking fetchById(Integer id);
    User getById(Integer id);
    String save(BookingPojo bookingPojo);
    String saveAdmin(AdminBooking adminBooking);

    String submitFeedback(FeedbackPojo feedbackPojo);
    List<Feedback> fetchAllFeedback();
    List<Booking> findApplicationById(Integer id);


    //    Contact fetchById(Integer id);
    void deleteById(Integer id);
    void deleteFeedback(Integer id);
    void deletecomment(Integer id);



    void processPasswordResetRequest(String email);

    void resetPassword(String email, String OTP, String password);

    User findByEmail(String email);
//    String save(BlogPojo blogPojo);
//    UserPojo findByEmail(String email);

//    List<Booking> getByKeyword( String keyword);
//    Page<Booking> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}
