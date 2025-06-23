package org.scoula.sample.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public Integer doAdd(String str1, String str2) throws Exception {
        return Integer.parseInt(str1) + Integer.parseInt(str2);
        //doAdd("100", "200") --> 100 + 200
        //숫자로 바꿀 수 없을 땐 예외 발생
        //doAdd("aaa", "200") --> exception
    }
}
