package cc.mrbird.system.log.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import cc.mrbird.common.poi.Excel;

/**
 * <p>
 * 
 * </p>
 *
 * @author yux
 * @since 2019-07-16
 */
public class Loginfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("LogId")
    @Excel(name="主键")
    private Integer LogId;

    /**
     * 标题
     */
    @TableField("LogTitle")
    @Excel(name="标题")
    private String LogTitle;

    /**
     * 评论
     */
    @TableField("LogContent")
    @Excel(name="评论")
    private String LogContent;


    public Integer getLogId() {
        return LogId;
    }

    public void setLogId(Integer LogId) {
        this.LogId = LogId;
    }

    public String getLogTitle() {
        return LogTitle;
    }

    public void setLogTitle(String LogTitle) {
        this.LogTitle = LogTitle;
    }

    public String getLogContent() {
        return LogContent;
    }

    public void setLogContent(String LogContent) {
        this.LogContent = LogContent;
    }

    @Override
    public String toString() {
        return "Loginfo{" +
        "LogId=" + LogId +
        ", LogTitle=" + LogTitle +
        ", LogContent=" + LogContent +
        "}";
    }
}
