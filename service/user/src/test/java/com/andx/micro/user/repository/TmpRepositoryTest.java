package com.andx.micro.user.repository;

import com.andx.micro.support.jpa.model.CheckType;
import com.andx.micro.user.BaseTest;
import com.andx.micro.user.model.Tmp;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by andongxu on 17-6-8.
 */
public class TmpRepositoryTest extends BaseTest {

    @Autowired
    private TmpRepository tmpRepository;

    @Test
    public void save1() {
        for (int i = 2; i < 10; i++) {
            Tmp tmp = new Tmp();
            tmp.setOne("o" + i);
            tmp.setTwo("t" + i);
            tmp.setCheckType(CheckType.WAIT);
            tmp = tmpRepository.save(tmp);
            Assert.assertNotNull(tmp.getId());

        }
    }

    @Test
    public void save2() {
        Tmp tmp = new Tmp();
        tmp.setOne("o r");
        tmp.setTwo("t r");
        tmp.setCheckType(CheckType.REJECT);
        tmp = tmpRepository.save(tmp);
        Assert.assertNotNull(tmp.getId());

    }

    @Test
    public void query1() {
        List<Tmp> tmps = tmpRepository.findAll(new Sort(Sort.Direction.DESC, "createdTime"));
        for (Tmp tmp : tmps) {
            System.out.println(tmp.getId() + "\t" + tmp.getOne() + "\t" + tmp.getCheckType());
        }
    }

    @Test
    public void findByCheckType() {
        List<Tmp> tmps = tmpRepository.findByCheckType(CheckType.PASS);
        for (Tmp tmp : tmps) {
            System.out.println(tmp.getId() + "\t" + tmp.getOne() + "\t" + tmp.getCheckType());
        }
    }


    @Test
    public void findByCheckTypeIn() {
        CheckType [] checkTypes = new CheckType [] {CheckType.REJECT, CheckType.NO};
        List<Tmp> tmps = tmpRepository.findByCheckTypeIn(Arrays.asList(checkTypes), new Sort(Sort.Direction.ASC, "createdTime"));
        for (Tmp tmp : tmps) {
            System.out.println(tmp.getId() + "\t" + tmp.getOne() + "\t" + tmp.getCheckType());
        }
    }
}
