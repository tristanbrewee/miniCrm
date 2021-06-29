package com.syntra.tristanbrewee.miniCrm.model.dtos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompleteCommunityTest {

    private CompleteCommunity completeCommunity;
    private Integer communityId1;
    private Integer communityId2;
    private String description1;
    private String description2;
    private LocalDate since1;
    private LocalDate since2;
    private LocalDate until1;
    private LocalDate until2;

    @BeforeEach
    void setUp() {
        completeCommunity = new CompleteCommunity();
        communityId1 = 1;
        communityId2 = 2;
        completeCommunity.setCommunityId(communityId1);
        description1 = "DescriptionOne";
        description2 = "DescriptionTwo";
        completeCommunity.setDescription(description1);
        since1 = LocalDate.now();
        since2 = LocalDate.now().minusDays(1);
        completeCommunity.setSince(since1);
        until1 = LocalDate.now();
        until2 = LocalDate.now().minusDays(1);
        completeCommunity.setUntil(until1);
    }

    @AfterEach
    void tearDown() {
        completeCommunity = null;
        communityId1 = null;
        communityId2 = null;
        description1 = null;
        description2 = null;
        since1 = null;
        since2 = null;
        until1 = null;
        until2 = null;
    }

    @Test
    void getCommunityId() {
        assertEquals(completeCommunity.getCommunityId(), communityId1);
    }

    @Test
    void setCommunityId() {
        completeCommunity.setCommunityId(communityId2);
        assertEquals(completeCommunity.getCommunityId(), communityId2);
        assertNotEquals(completeCommunity.getCommunityId(), communityId1);
    }

    @Test
    void getDescription() {
        assertEquals(completeCommunity.getDescription(), description1);
    }

    @Test
    void setDescription() {
        completeCommunity.setDescription(description2);
        assertEquals(completeCommunity.getDescription(), description2);
        assertNotEquals(completeCommunity.getDescription(), description1);
    }

    @Test
    void getSince() {
        assertEquals(completeCommunity.getSince(), since1);
    }

    @Test
    void setSince() {
        completeCommunity.setSince(since2);
        assertEquals(completeCommunity.getSince(), since2);
        assertNotEquals(completeCommunity.getSince(), since1);
    }

    @Test
    void getUntil() {
        assertEquals(completeCommunity.getUntil(), until1);
    }

    @Test
    void setUntil() {
        completeCommunity.setUntil(until2);
        assertEquals(completeCommunity.getUntil(), until2);
        assertNotEquals(completeCommunity.getUntil(), until1);
    }
}