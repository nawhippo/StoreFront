import React, { useState } from "react";
import SizeSlider from './SizeSlider';
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
          <div class="card">
            <img class="card-img-top" src={productState} alt={productState.name}></img>
            <div className="card-title">
            {product.name}
            </div>
            <div className="card-body">
              {product.description}
            </div>
            <p className="card-text">
            {product.price}
            </p>
            <SizeSlider></SizeSlider>
          </div>  
    );
}