// 代码生成时间: 2025-10-08 18:30:30
package com.example.soundeffectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/sound-effects")
public class SoundEffectManager {

    public static void main(String[] args) {
        SpringApplication.run(SoundEffectManager.class, args);
    }

    // 模拟音效数据
    private static final String[] SOUND_EFFECTS = {"explosion", "laser", "jump", "coin"};

    // 获取所有音效
    @GetMapping("/")
    public String[] getAllSoundEffects() {
        return SOUND_EFFECTS;
    }

    // 根据名称获取音效
    @GetMapping("/{name}")
    public String getSoundEffect(@PathVariable String name) {
        for (String soundEffect : SOUND_EFFECTS) {
            if (soundEffect.equals(name)) {
                return soundEffect;
            }
        }
        throw new SoundEffectNotFoundException("Sound effect not found: " + name);
    }

    // 异常处理
    @ExceptionHandler(SoundEffectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleSoundEffectNotFoundException(SoundEffectNotFoundException ex) {
        return ex.getMessage();
    }

    // 自定义异常类
    static class SoundEffectNotFoundException extends RuntimeException {
        public SoundEffectNotFoundException(String message) {
            super(message);
        }
    }
}