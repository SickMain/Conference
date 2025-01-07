package com.example.conference.repository.contests;

import com.example.conference.contests.Conference;
import com.example.conference.contests.TeamRecord;
import com.example.conference.contests.UserInTeamRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ConferenceRepository extends PagingAndSortingRepository<Conference, Long>, CrudRepository<Conference, Long> {

    Page<Conference> findAll(Pageable pageable);

    @Query("SELECT t, sum(c.mark) as tmark FROM TeamRecord t join t.criteriaMarks c where t.conference.id = :conferenceId group by t.id order by tmark DESC")
    Set<TeamRecord> sortedTeamRecordsByConferenceId(@Param("conferenceId") Long id);

    @Query("SELECT case WHEN count(*) > 0 THEN true ELSE false END " +
            "FROM UserInTeamRecord u join u.teamRecord tr join tr.conference c " +
            "where c.id = :conferenceId and u.username in (:usernames)")
    boolean isAlreadyRegistered(@Param("conferenceId") Long conferenceId, @Param("usernames") List<String> usernames);

}
