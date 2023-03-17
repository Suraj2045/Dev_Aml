package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.BranchParam;
import com.nellinfotech.aml.entities.NoiseWord;
import com.nellinfotech.aml.model.Header;

public interface BranchService {
    
    BranchParam saveBranch(Header header, BranchParam branchParam);
    
    BranchParam updateBranch(Header header, BranchParam branchParam);
    
    List<BranchParam> getBranchList();
    
    String deleteBranch(BranchParam branchParam);

	NoiseWord saveNoiseWord(Header header, NoiseWord noiseWord);

	List<NoiseWord> getNoiseWordByBankCode(String bankCode);

	NoiseWord updateNoiseWord(Header header, NoiseWord noiseWord);
    
}
