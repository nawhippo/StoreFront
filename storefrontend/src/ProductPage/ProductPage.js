import react, {useEffect, useState} from "react";

const productPage = () => {
    const [products, setProducts] = useState([]);
    const [message, setMessage] = useState('');
    const [searchFilter, setSearchFilter] = useState('');
    let stream; 
    useEffect = (() => {
        getAllProducts();
    })

const handleSearchbarChange = () => {
    setSearchFilter()
}

const searchTermMatches = (term, productName) => {
    if ((term == "") || (productName.contains(term))) {
        return true;
    } 
    return false;
}


    const getAllProducts = () => {
        fetch("/getAllProducts", {
        method: 'GET'
        })
        .then(response => {
            response.json();
            setProducts(response.products)

        })
        .catch(error => 
            setMessage("Something went wrong while trying to get products :(")
        )
    }
    

    
    return (
        <div>
            <div>
                <input type="text" onChange={handleSearchbarChange}></input>
                products ? {products.filter(searchTermMatches).map((product,index) => (
                    <div key={index}>
                        <ProductCard product={product} />
                    </div>
                ))} 
                <p>no products found</p>   
            </div>
        </div>
    )
}