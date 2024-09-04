import { useEffect, useState } from "react"
import { useUserContext } from "../userContext"
import { Accordion } from "react-bootstrap";
import { AccordionDetails, AccordionSummary, toggleButtonClasses, Typography } from "@mui/material";
import ProductCard from "../ProductPage/ProductCard";

const ShoppingCartDisplay = () => {
    const user = useUserContext();
    ShoppingCart = useState(user.ShoppingCart);
    const [totalPrice, setTotalPrice] = useState(0);
useEffect(() => {
calculationTotalPrice();
}, [totalPrice]);


const calculationTotalPrice = () => {
    for (const item in ShoppingCart){
        totalPrice += item.price;
    }
}

return (
    <div>
    <button on onClick={toggle} />
    {toggle && (
        <div>
      {ShoppingCart.length > 0 ? (
        ShoppingCart.map(item => (
          <Accordion key={item.id}>
            <AccordionSummary>
              <Typography>{item.name} ({item.quantity})</Typography>
            </AccordionSummary>
            <AccordionDetails>
              <ProductCard Product={item} />
            </AccordionDetails>
          </Accordion>
        ))) : (
        <Typography>No Items to Display</Typography>
    )}
      {totalPrice > 0 && (
        <Typography>Total Price: ${totalPrice}</Typography>
      )}
      </div>
    )}
    </div>
  );
};

export default ShoppingCartDisplay;