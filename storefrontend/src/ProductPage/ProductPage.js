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

const searchTermMatches = (term, product) => {
    if ((term == "") || (product.name.contains(term))) {
        return true;
    } 
    return false;
}


    const getAllProducts = () => {
        fetch("/getAllProducts", {
        method: 'GET'
        })
        .then(response => {
            setProducts(response)

        })
        .catch(error => 
            setMessage("Something went wrong while trying to get products :(")
        )
    }
   
    const filteredProducts = products.filter(product => searchTermMatches(searchFilter, product));

    
    return (
        <div>
            <div>
                <input type="text" onChange={handleSearchbarChange}></input>
                {filteredProducts.length > 0 ? (
                    filteredProducts.map((product, index) => (
                    <div key={index}>
                        <ProductCard product={product} />
                    </div>
                ))
            ) : ( <p>no products found</p>  
            )} 
            </div>
        </div>
    )
}