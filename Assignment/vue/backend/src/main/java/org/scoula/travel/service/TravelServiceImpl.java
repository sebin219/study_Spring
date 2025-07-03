package org.scoula.travel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.travel.dto.TravelDTO;
import org.scoula.travel.dto.TravelImageDTO;
import org.scoula.travel.mapper.TravelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
    final TravelMapper mapper;

    @Override
    public Page<TravelDTO> getPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public List<TravelDTO> getList() {
        return List.of();
    }

    @Override
    public TravelDTO get(Long no) {
        return null;
    }

    @Override
    public TravelImageDTO getImage(Long no) {
        return null;
    }
}
