/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OwnerChangeVO
 * Author:   Administrator
 * Date:     2018/9/28 16:30
 * Description: 犬主更换VO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.VO;

import com.entities.TOwnerChange;

/**
 * 〈一句话功能简述〉<br> 
 * 〈犬主更换VO〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
public class OwnerChangeVO extends TOwnerChange{
    private String oldDogOwnerName;
    private String newDogOwnerName;

    public String getOldDogOwnerName() {
        return oldDogOwnerName;
    }

    public void setOldDogOwnerName(String oldDogOwnerName) {
        this.oldDogOwnerName = oldDogOwnerName;
    }

    public String getNewDogOwnerName() {
        return newDogOwnerName;
    }

    public void setNewDogOwnerName(String newDogOwnerName) {
        this.newDogOwnerName = newDogOwnerName;
    }
}
