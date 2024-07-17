import react, {useState} from "react";

const productPage = () => {
    const [products, setProducts] = useState([]);
    const [message, setMessage] = useState('');
    const [searchFilter, setSearchFilter] = useState('');
    let stream; 
    
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
            <input type="text"> </input>
            <div>
                products ? {products.filter(searchTermMatches).map((product,index) => (
                    <div className="col-md-4" key={index}>
                        <ProductCard product={product} />
                    </div>
                ))} 
                <p>no products found</p>   
            </div>
        </div>
    )
}