package com.example.conference.converter;

import com.example.conference.contests.TeamRecord;
import com.example.conference.exceptions.TeamNotFoundException;
import com.example.conference.repository.contests.TeamRecordRepository;
import com.example.conference.repository.contests.UserInTeamRecordRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringIdToTeamRecordConverter implements Converter<String, TeamRecord> {

    private final TeamRecordRepository teamRecordRepository;

    public StringIdToTeamRecordConverter(TeamRecordRepository teamRecordRepository) {
        this.teamRecordRepository = teamRecordRepository;
    }

    @Override
    public TeamRecord convert(String teamRecordId) {
       return teamRecordRepository.findById(Long.valueOf(teamRecordId)).orElseThrow(() -> new TeamNotFoundException());
    }
}
