package com.syntra.tristanbrewee.miniCrm.persistance.services;

import com.syntra.tristanbrewee.miniCrm.model.Member;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.model.idclasses.MemberId;
import com.syntra.tristanbrewee.miniCrm.persistance.repositories.MemberRepository;
import com.syntra.tristanbrewee.miniCrm.utils.Conversions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImp implements MemberService {

    @PersistenceContext
    private EntityManager entityManager;

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImp(MemberRepository memberRepository, EntityManager entityManager) {
        this.memberRepository = memberRepository;
        this.entityManager = entityManager;
    }

    public void saveCompletePerson(CompletePerson completePerson) {
        Conversions.completePersonToMemberList(completePerson)
                .stream()
                .forEach(e ->
                        memberRepository.save(e)
                );
    }

    public void saveMember(Member member){
        memberRepository.save(member);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public List<Member> getByCommunityId(Integer communityId){
        return  memberRepository.findAll()
                .stream()
                .filter(e -> e.getMemberId().getCommunity_id().equals(Integer.valueOf(communityId)))
                .sorted(
                        Comparator.comparing(Member::getUntil, Comparator.nullsFirst(Comparator.naturalOrder()))
                        .thenComparing(Member::getSince)
                )
                .collect(Collectors.toList());
    }

    public void subscribePersonByCommunityIdAndPersonId(Integer communityId, Integer personId){
        Member newMember = new Member();
        newMember.setMemberId(new MemberId(communityId, personId));
        newMember.setSince(LocalDate.now());
        newMember.setUntil(null);
        memberRepository.save(newMember);
    }

    public void reSubscribePersonByCommunityIdAndPersonId(Integer communityId, Integer personId){
        Member newMember = memberRepository.findAll()
                .stream()
                .filter(e -> e.getMemberId().getCommunity_id() == communityId)
                .filter(e -> e.getMemberId().getPerson_id() == personId)
                .collect(Collectors.toList())
                .get(0);
        newMember.setSince(LocalDate.now());
        newMember.setUntil(null);
        memberRepository.save(newMember);
    }

    public void unsubscribePersonByCommunityIdAndPersonId(Integer communityId, Integer personId){
        Member newMember = memberRepository.findAll()
                .stream()
                .filter(e -> e.getMemberId().getCommunity_id() == communityId)
                .filter(e -> e.getMemberId().getPerson_id() == personId)
                .collect(Collectors.toList())
                .get(0);
        newMember.setUntil(LocalDate.now());
        memberRepository.save(newMember);
    }

    public Member getByPersonIdAndCommunityId(Integer personId, Integer communityId){
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM member " +
                        "WHERE member.person_id = " + personId +" " +
                        "AND member.community_id = " + communityId
        );
        List<Object[]> x = query.getResultList();
        if (x.isEmpty())
            return null;
        Member member = new Member();
        MemberId memberId = new MemberId();
        memberId.setPerson_id((Integer) (x.get(0)[0]));
        memberId.setCommunity_id((Integer) (x.get(0)[1]));
        member.setMemberId(memberId);
        LocalDate since;
        if (x.get(0)[2] != null) {
            Date date = (Date) (x.get(0)[2]);
            since = date.toLocalDate();
        }
        else
            since = null;
        member.setSince(since);
        LocalDate until;
        if (x.get(0)[2] != null) {
            Date date = (Date) (x.get(0)[3]);
            until = date.toLocalDate();
        }
        else
            until = null;
        member.setUntil(until);
        return member;
    }
}
