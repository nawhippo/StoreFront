import React, { useState } from "react";
import { CardActionArea, CardMedia, Slider, Card } from "@mui/material";
const ProductCard = ( {Product} ) => {
    const [productState, setProductState] = useState(Product);
    let imgsource;
    if(productState.image){
    const imgsource = `./productImages/${productState.image}`;
    } else {
        console.log("no image found for product")
        imgsource = null;
    }

    return (
      <Card>
        <Card Header>{Product.name}</Card>
        <CardActionArea>
          {Product.image ? (
            <CardMedia src=""/>
          ) : (
            null
          )
          }
        <Card></Card>
      </CardActionArea>
      </Card>
    );
}
export default ProductCard;