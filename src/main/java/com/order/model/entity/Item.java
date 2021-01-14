package com.order.model.entity;

import com.order.constants.Constants;
import com.order.model.common.BaseModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Constants.TBL_ITEM_INFO)
@Data
public class Item extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "name")
    private String name;

}
