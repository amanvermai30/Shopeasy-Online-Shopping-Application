// getting product id , session key from localstorage

const sessionKey = localStorage.getItem("sessionkey");
const productId = localStorage.getItem("productId");
const userId = localStorage.getItem("userId");

let getSingalProductData = async () => {
    try {
        const res = await fetch(
            `http://shopeasy-env.eba-xkxpqfpn.ap-south-1.elasticbeanstalk.com/productController/singalproduct/${sessionKey}/${productId}`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            }
        );

        if (res.ok) {
            console.log("success");
            const data = await res.json();
            return data;
        } else {
            const data = await res.json();
            const error = JSON.stringify(data);
            const msg = JSON.parse(error);
            console.log(msg);
            throw new Error(msg.message);
        }
    } catch (error) {
        console.log(error);
        throw new Error(error);
    }
};

//   after calling I am appending data in html
let appendData = (data) => {
    // taking main container for append each product which is inside a tag
    const imgContainer = document.querySelector("#big-img");
    const detailsContainer = document.querySelector("#product-detail");
    const priceContainer = document.querySelector("#price-div");

    // // creating a tag, img tag, p tag and span tag
    const img = document.createElement("img");
    const h1 = document.createElement("h1");
    const p1 = document.createElement("p");
    const h2 = document.createElement("h1");
    const label = document.createElement("label");
    const p2 = document.createElement("p");

    // now I will set class for atag p2 and span tag only
    img.setAttribute("id", "bigImg");
    p1.setAttribute("id", "productInfo");
    h2.setAttribute("id", "price");
    p2.setAttribute("id", "discount");
    label.setAttribute("for", "off");

    // add data to all tags
    img.src = data.picture;
    h1.innerText = data.productName;
    p1.innerText = "At shopeasy you can find your best choice product at low price and high quality, buy now we are having limited edition. hurry up!"
    h2.innerText = "₹" + data.afterDiscountPrice
    label.innerText = data.discount + "%";
    p2.innerText = "₹" + data.marketPrice + "  Market Price";

    // now I will append all tags
    imgContainer.append(img);
    detailsContainer.append(h1, p1);
    priceContainer.append(h2, label, p2)
};


// // calling  api for singal product data after clicking on it
window.addEventListener("load", async () => {
    const productData = await getSingalProductData();
    appendData(productData);
    checkQuantity(productData);
});



let goodsValue = 0;
function checkQuantity(data) {

    let minus = document.getElementById("minus");
    let plus = document.getElementById("plus");
    let value = document.getElementById("value");
    let itemNumberInCart = document.querySelector(".itemNumber");
    const productQuantity = data.quantity;

    // function for change the value of product
    minus.onclick = function () {
        if (goodsValue <= 0) {
            emptyItems();
        } else {
            goodsValue--;
            value.innerText = goodsValue;
            itemNumberInCart.innerText = goodsValue;
        }
    };
    plus.onclick = function () {

        if (goodsValue >= productQuantity) {
            alert("Sorry customer currently product available quantity is  " + productQuantity)
        } else {
            goodsValue++;
            value.innerText = goodsValue;
            itemNumberInCart.innerText = goodsValue;
        }

    };

}
checkQuantity();




// calling add to cart api here

async function addToCartData() {

    try {
        let res = await fetch(`http://shopeasy-env.eba-xkxpqfpn.ap-south-1.elasticbeanstalk.com//customerController/addtocart/${productId}/${userId}/${goodsValue}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (res.ok) {
            console.log("sucesss");
            let data = await res.json();
            return data;

        } else {
            let data = await res.json();
            let error = JSON.stringify(data);

            let msg = JSON.parse(error);

            console.log(msg);
            alert(msg.message);
        }

    } catch (error) {
        console.log(error);
    }

}

async function addToCartFun() {
    try {
        const cart = await addToCartData();
        console.log(cart);
        alert("product added to cart successfuly");
        window.location.href = "/customer assets/cart/cart.html";
    } catch (error) {
        console.log(error);
    }
}



