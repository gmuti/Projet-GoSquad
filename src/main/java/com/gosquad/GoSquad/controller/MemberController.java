package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.Member;
import com.gosquad.GoSquad.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/members")
    public ResponseEntity list() {
        return new ResponseEntity(service.getMembers(), HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity createMember(@RequestBody Member member) {
        return new ResponseEntity(service.createMember(member), HttpStatus.CREATED);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity updateMember(@PathVariable("id") String id, @RequestBody Member member) {
        member.setId(id);
        return new ResponseEntity(service.updateMember(member), HttpStatus.OK);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteMember(id), HttpStatus.OK);
    }
}
