package red.aoi.madlibs;

import java.io.Serializable;

/**
 * Created by Aoi on 2018-03-26.
 */

class Story implements Serializable {

    /**
     * 根据内置的模板文件创建一个 Story 实例
     * @return
     */
    public static Story fromTemplate() {
        return new Story(); // TODO:
    }

    private String contents;
}
