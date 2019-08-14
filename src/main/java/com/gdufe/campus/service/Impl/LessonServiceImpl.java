package com.gdufe.campus.service.Impl;

import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.exception.BusinessException;
import com.gdufe.campus.mapper.LessonMapper;
import com.gdufe.campus.pojo.DO.LessonDO;
import com.gdufe.campus.pojo.DTO.LessonDTO;
import com.gdufe.campus.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bravery
 * @date 2019/8/12 15:20
 */
@Service
@Slf4j
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonMapper lessonMapper;


    @Override
    public List<LessonDTO> listLessons() {
        try {
            List<LessonDO> lessons = lessonMapper.findAll();
            ArrayList<LessonDTO> lessonDTOS = new ArrayList<>();
            for (LessonDO l : lessons) {
                LessonDTO lessonDTO = new LessonDTO();
                BeanUtils.copyProperties(l, lessonDTO);
                lessonDTOS.add(lessonDTO);
            }
            return lessonDTOS;
        } catch (BusinessException e) {
            log.error("[代课] 查询全部代课失败");
            throw new BusinessException(ResultEnum.SYSTEM_ERROR);
        }

    }

    @Override
    public List<LessonDTO> findByUser(Long uid) {
        if (uid == null || uid <= 0) {
            log.error("[代课] 用户代课查询uid为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        try {
            List<LessonDO> lessonDOS = lessonMapper.findByUser(uid);
            ArrayList<LessonDTO> lessonDTOS = new ArrayList<>();
            for (LessonDO l : lessonDOS) {
                LessonDTO lessonDTO = new LessonDTO();
                BeanUtils.copyProperties(l, lessonDTO);
                lessonDTOS.add(lessonDTO);
            }
            return lessonDTOS;
        } catch (BusinessException e) {
            log.error("[代课] 查询全部代课失败");
            throw new BusinessException(ResultEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public LessonDTO findById(Long id) {
        if (id == null || id <= 0) {
            log.error("[代课] 代课查询id为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        LessonDO lessonDO = lessonMapper.findById(id);
        if (lessonDO == null) {
            log.info("[代课] ID查询代课为空");
            return null;
        } else {
            LessonDTO lessonDTO = new LessonDTO();
            BeanUtils.copyProperties(lessonDO, lessonDTO);
            return lessonDTO;
        }
    }

    @Override
    public Integer addLesson(LessonDTO lessonDTO) {
        if (lessonDTO == null) {
            log.error("[代课] 新增代课参数为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        LessonDO lessonDO = new LessonDO();
        BeanUtils.copyProperties(lessonDTO, lessonDO);
        lessonDO.setIsTake(0);
        lessonDO.setLastTime(System.currentTimeMillis());
        int save = lessonMapper.save(lessonDO);
        return save;
    }

    @Override
    public Integer UpdateLesson(LessonDTO lessonDTO) {
        if (lessonDTO == null || lessonDTO.getId() == null) {
            log.error("[代课] 新增代课参数为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        LessonDO lessonDO = new LessonDO();
        BeanUtils.copyProperties(lessonDTO, lessonDO);
        int update = lessonMapper.update(lessonDO);
        return update;
    }

    @Override
    public Integer DeleteLesson(Long id) {
        if (id == null) {
            log.error("[代课] 删除代课参数ID为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        Integer delete = lessonMapper.deleteById(id);
        return delete;
    }

    @Override
    public boolean taken(Long id) {
        if (id == null) {
            log.error("[代课] 代课接单参数ID为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        LessonDTO lessson = findById(id);
        if (lessson.getIsTake() == 0) {
            lessson.setIsTake(1);
            UpdateLesson(lessson);
            return true;
        } else {
            log.info("[代课] 代课重复已接单");
            return false;
        }

    }
}
