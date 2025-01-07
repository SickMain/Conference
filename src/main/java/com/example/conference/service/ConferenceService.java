package com.example.conference.service;

import com.example.conference.contests.Conference;
import com.example.conference.contests.CriteriaMark;
import com.example.conference.contests.TeamRecord;
import com.example.conference.contests.UserInTeamRecord;
import com.example.conference.exceptions.ConferenceNotFoundException;
import com.example.conference.exceptions.TeamMembersAlreadyRegisteredException;
import com.example.conference.repository.contests.ConferenceRepository;
import com.example.conference.repository.contests.CriteriaMarkRepository;
import com.example.conference.repository.contests.TeamRecordRepository;
import com.example.conference.repository.contests.UserInTeamRecordRepository;
import com.example.conference.repository.teams.UserInTeamRepository;
import com.example.conference.teams.Team;
import com.example.conference.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Transactional
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    private final UserInTeamRecordRepository userInTeamRecordRepository;

    private final CriteriaMarkRepository criteriaMarkRepository;

    private final TeamRecordRepository teamRecordRepository;

    public ConferenceService(ConferenceRepository conferenceRepository,
                             UserInTeamRecordRepository userInTeamRecordRepository,
                             CriteriaMarkRepository criteriaMarkRepository,
                             TeamRecordRepository teamRecordRepository) {
        this.conferenceRepository = conferenceRepository;
        this.userInTeamRecordRepository = userInTeamRecordRepository;
        this.criteriaMarkRepository = criteriaMarkRepository;
        this.teamRecordRepository = teamRecordRepository;
    }

    public void registerConference(Conference conference) {
        conferenceRepository.save(conference);
    }

    public List<Conference> getConferencePage(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 3, Sort.by("registrationClosing").descending());
        return conferenceRepository.findAll(pageable).stream().toList();

    }

    public int getTotalPages() {
        Pageable pageable = PageRequest.of(0, 3);
        return conferenceRepository.findAll(pageable).getTotalPages();

    }

    public List<Conference> getRecentConference() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("registrationClosing").descending());
        return conferenceRepository.findAll(pageable).stream().toList();
    }

    public Optional<Conference> findConferenceById(Long id) {
        return conferenceRepository.findById(id);
    }

    public Conference findConferenceByIdOrThrown(Long id) {
        return conferenceRepository.findById(id).orElseThrow(() -> new ConferenceNotFoundException());
    }

    public void registerTeam(Conference conference, Team team) {
        List<String> usernames = team.getUserInTeam().stream().map(userInTeam -> userInTeam.getUser().getUsername()).toList();
        if (conferenceRepository.isAlreadyRegistered(conference.getId(), usernames)) {
            throw new TeamMembersAlreadyRegisteredException(conference.getId());
        }

        TeamRecord teamRecord = new TeamRecord(team);
        Set<UserInTeamRecord> userInTeamRecordSet = team.getUserInTeam()
                .stream()
                .filter(userInTeam -> userInTeam.isActive())
                .map(userInTeam -> new UserInTeamRecord(userInTeam))
                .peek(userInTeamRecord -> userInTeamRecord.setTeamRecord(teamRecord))
                .collect(Collectors.toSet());

        teamRecord.setConference(conference);
        teamRecord.setDefaultMarks(conference.getCriteriaSet());

        userInTeamRecordRepository.saveAll(userInTeamRecordSet);
    }

    public Set<TeamRecord> sortedTeamRecordsBySummaryMark(Long conferenceId) {
        return conferenceRepository.sortedTeamRecordsByConferenceId(conferenceId);
    }

    public List<UserInTeamRecord> teamRecordsUsers(Long teamRecordId){
        return userInTeamRecordRepository.teamRecordsUsers(teamRecordId);
    }

    public void saveAllCriteriaMarks(Collection<CriteriaMark> criteriaMarks){
        criteriaMarkRepository.saveAll(criteriaMarks);
    }

    public List<User> findAllUsersByIds(Collection<Long> ids){
        return userInTeamRecordRepository.usersByIds(ids);
    }

    public void disqualifyTeam(Long teamRecordId){
        List<UserInTeamRecord> userInTeamRecords = teamRecordsUsers(teamRecordId);
        teamRecordRepository.deleteById(teamRecordId);
        userInTeamRecordRepository.deleteAll(userInTeamRecords);
    }

}
