package com.lvh.pojo;

import com.lvh.pojo.Motel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-20T22:37:54")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Date> createdDate;
    public static volatile SingularAttribute<Image, Motel> motelId;
    public static volatile SingularAttribute<Image, String> publicUrl;
    public static volatile SingularAttribute<Image, String> description;
    public static volatile SingularAttribute<Image, Long> id;
    public static volatile SingularAttribute<Image, Date> updatedDate;
    public static volatile SingularAttribute<Image, String> url;

}