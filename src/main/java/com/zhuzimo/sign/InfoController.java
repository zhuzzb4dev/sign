package com.zhuzimo.sign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("info")
public class InfoController {

    @Resource
    private PtEntityDao ptEntityDao;


    @PostMapping()
    public ResponseEntity add(@RequestBody @Validated PtEntity entity){
        entity.setId(null);
        ptEntityDao.save(entity);
        return ResponseEntity.ok(entity.getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ptEntityDao.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody @Validated PtEntity entity){
        ptEntityDao.save(entity);
        return ResponseEntity.ok(entity.getId());
    }

    @GetMapping("{id}")
    public ResponseEntity queryById(@PathVariable Long id){
        return ResponseEntity.ok(ptEntityDao.findById(id));
    }

    @GetMapping()
    public ResponseEntity queryAll(){
        Iterable<PtEntity> all = ptEntityDao.findAll();
        return ResponseEntity.ok(all);
    }
}
