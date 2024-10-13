package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.branch.AddBranchDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.branch.BranchDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.BranchMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.branch.BranchRepository;
import com.virtusa.springboot.BankingSystemSpringBoot.service.branch.BranchService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/admin/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchMapper branchMapper;

    @Autowired
    private BranchRepository branchRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getBranch/{branchId}")
    public ResponseEntity<?> getBranchById(@PathVariable("branchId") Long branchId) {
        Branch branch = branchService.getBranchById(branchId);
        return ResponseEntity.status(HttpStatus.OK).body(branch);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addBranch")
    public ResponseEntity<?> addBranch(@Valid @RequestBody AddBranchDto addBranchDto) {
        Branch branch = branchService.addBranch(branchMapper.toEntity(addBranchDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(branch);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateBranch/{branchId}")
    public ResponseEntity<?> updateBranch(@Valid @PathVariable("branchId") Long branchId,
            @RequestBody AddBranchDto branchDto) {
        Branch branch = branchService.updateBranch(branchId, branchMapper.toEntity(branchDto));
        return ResponseEntity.ok().body(branch);
    }

    @GetMapping("/getAllBranches")
    public ResponseEntity<List<BranchDto>> getAllBranches() {

        List<BranchDto> allBranches = branchService.getAllBranch().stream().map(b -> branchMapper.toDto(b))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allBranches);
    }

    @GetMapping("getAllIfsc")
    public ResponseEntity<List<String>> getAllIfsc() {
        List<String> ifsc = branchRepository.findAll().stream().map(b -> b.getIfsc()).collect(Collectors.toList());
        return ResponseEntity.ok().body(ifsc);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deleteBranch/{branchId}")
    public ResponseEntity<ResponseDto> deleteBranch(@PathVariable Long branchId) {
        branchService.deleteBranch(branchId);
        ResponseDto response = new ResponseDto();
        response.setMessage("Branch with id = " + branchId + " successfully!!!.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
