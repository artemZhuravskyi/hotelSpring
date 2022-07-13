package com.example.demo.service;

import com.example.demo.DAO.ApplicationRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.model.Application;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.model.Application.Status.*;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final RoomRepository roomRepository;

    public void createApplication(ApplicationDTO applicationDTO, User currentUser) {
        Application application = Application.builder()
                .client(currentUser)
                .guestsNum(applicationDTO.getGuestsNum())
                .lengthOfStay(applicationDTO.getLengthOfStay())
                .roomClass(applicationDTO.getRoomClass())
                .status(ON_MANAGER_REVIEW)
                .build();
        applicationRepository.save(application);
    }



    public List<Application> showAllRequests(User currentUser) {
        return applicationRepository.findAllByClient(currentUser);
    }

    @Transactional
    public void sendApplicationRequest(Long id, Application application) {

        Application app = applicationRepository.findById(application.getId()).get();
        Room ro = roomRepository.findById(id).get();
        app.setRoom(ro);
        app.setStatus(ON_CLIENT_REVIEW);
    }



}
