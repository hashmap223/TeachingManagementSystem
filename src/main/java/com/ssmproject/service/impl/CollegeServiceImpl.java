package com.ssmproject.service.impl;

import com.ssmproject.mapper.CollegeMapper;
import com.ssmproject.po.College;
import com.ssmproject.po.CollegeExample;
import com.ssmproject.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  HashMap
 *
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    public List<College> finAll() throws Exception {
        CollegeExample collegeExample = new CollegeExample();
        CollegeExample.Criteria criteria = collegeExample.createCriteria();

        criteria.andCollegeidIsNotNull();


        return collegeMapper.selectByExample(collegeExample);
    }
}
