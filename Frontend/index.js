//  get vendor profile- get request

const sessionKey = localStorage.getItem("sessionKey");
console.log(sessionKey);

let getProductData = async () => {
    try {
        const res = await fetch(
            `http://shopeasy-env.eba-xkxpqfpn.ap-south-1.elasticbeanstalk.com/productController/viewallproducts`,
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
  const mainContainer = document.querySelector(".product--items__container");

    data.forEach((el,index)=>{

      // // creating a tag, img tag, p tag and span tag
    const atag = document.createElement("a");
    const img = document.createElement("img");
    const p1 = document.createElement("p");
    const p2 = document.createElement("p");
    const span1 = document.createElement("span");
    const span2 = document.createElement("span");

    // now I will set class for atag p2 and span tag only
    atag.classList.add("product--items__info")
    p2.classList.add("price2")
    span1.classList.add("price1");
    span2.classList.add("offer");


    // add data to all tags 
    img.src = el.picture;
    p1.innerText = el.productName;
    p2.innerText = "Price: "+"₹"+el.afterDiscountPrice;
    span1.textContent = el.marketPrice;
    span2.textContent = el.discount+"%";

    // by clicking any product it will redirect to singal product page 
    atag.addEventListener("click", () => {
      const productId = el.productId;
      localStorage.setItem('productId', productId);
      window.location.href = "./customer assets/singal product/singalProduct.html";
    });
    // now I will append all tags 
    atag.append(img, p1, p2, span1, span2);
    mainContainer.append(atag);

    })
};

// calling  api for vendor data after login
window.addEventListener("load", async () => {

    if(sessionKey == null ){
        alert("You have not logged in please login first");
        window.location.href = "./customer assets/customer login/login.html"
    
    }else {
        const logoutbtn = document.querySelector(".logout");
        logoutbtn.innerText = "logout"
        const customerData = await getProductData();
        appendData(customerData);
    }
    
});

// serch product by category
const aTags = document.querySelectorAll('.productCatogary a');

  for (let i = 0; i < aTags.length; i++) {
    
      aTags[i].addEventListener('click', function(event) {
      event.preventDefault(); 
      const clickedTag = event.target.textContent;
      localStorage.setItem('category', clickedTag);

      if(clickedTag == "Mens"){
        window.location.href = "./customer assets/category/mans/mans.html";

      }else if(clickedTag == "Womans"){
        window.location.href = "./customer assets/category/womans/womans.html";

      }else if(clickedTag == "Kids"){
        window.location.href = "/customer assets/category/kids/kids.html";

      }else {
        window.location.href = "/customer assets/category/grocery/grocery.html";
      }

    });
  }





