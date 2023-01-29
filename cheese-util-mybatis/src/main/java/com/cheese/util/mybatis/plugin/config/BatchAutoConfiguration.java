package com.cheese.util.mybatis.plugin.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.cheese.util.mybatis.plugin.selector.BatchAutoImportSelector;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 * @author sobann
 */
@Configuration
@Import(BatchAutoImportSelector.class)
@AutoConfigureAfter({MybatisPlusAutoConfiguration.class})
public class BatchAutoConfiguration {
}

