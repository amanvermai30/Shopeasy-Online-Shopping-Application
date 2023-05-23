// serch product by category
const aTags = document.querySelectorAll('.productCatogary a');

for (let i = 0; i < aTags.length; i++) {

  aTags[i].addEventListener('click', function (event) {
    event.preventDefault();
    const clickedTag = event.target.textContent;
    localStorage.setItem('category', clickedTag);

    if (clickedTag == "Mens") {
      window.location.href = "/Frontend/customer assets/category/mans/mans.html";

    } else if (clickedTag == "Womans") {
      window.location.href = "/Frontend/customer assets/category/womans/womans.html";

    } else if (clickedTag == "Kids") {
      window.location.href = "/Frontend/customer assets/category/kids/kids.html";

    } else {
      window.location.href = "/Frontend/customer assets/category/grocery/grocery.html";
    }

  });
}


const customerId = localStorage.getItem("userId");
const sessionKey = localStorage.getItem("sessionkey");

let getCartData = async () => {
  try {
    const res = await fetch(
      `http://localhost:8888/customerController/getcartdetails/${customerId}`,
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




function appendData(data) {

  const totalPriceInput = document.getElementById("totalprice");
  const total = data.totalPrice;
  const roundedNum = total.toFixed(2);
  localStorage.setItem("totalAmount", roundedNum);
  totalPriceInput.value = roundedNum;

  // setting product no in cart
  const numberOfProductInCart = data.numberOfProduct;
  localStorage.setItem("numberOfProductInCart", numberOfProductInCart);

  //now getting it from local storage  
  const getNoFromLocal = localStorage.getItem("numberOfProductInCart");
  const cartProductNo = document.querySelector(".itemNumber");
  cartProductNo.innerText = getNoFromLocal;

  // getting each product from cart details api
  data.products.forEach(function (el) {
    let tr = document.createElement("tr");

    // creating all td and setting text to it
    let prid = document.createElement("td");
    prid.innerText = el.productId;

    let prName = document.createElement("td");
    prName.innerText = el.productName;

    let quantity = document.createElement("td");
    quantity.innerText = el.quantity;

    let discount = document.createElement("td");
    discount.innerText = el.discount + "%";

    let AfterDiscountPrice = document.createElement("td");
    AfterDiscountPrice.innerText = el.afterDiscountPrice;

    let category_type = document.createElement("td");
    category_type.innerText = el.category;

    const cancel = document.createElement('td');
    const i = document.createElement('i');
    i.classList.add('fa-solid', 'fa-xmark', 'cancel');

    i.addEventListener('click', function () {
      // Get the product ID from the current row
      const productId = el.productId;

      // Make an API request to delete the product from the cart
      fetch(`http://localhost:8888/customerController/deleteproductfromcart/${productId}/${customerId}/${sessionKey}`, {
        method: 'DELETE'
      })
        .then(response => response.json())
        .then(data => {
          // Handle the response data
          console.log(data);
        })
        .catch(error => {
          // Handle errors
          console.error(error);
        });

      tr.remove();
    });
    cancel.appendChild(i);

    // in tr appending all td then appending to table
    tr.append(prid, prName, quantity, discount, category_type, AfterDiscountPrice, cancel);
    document.querySelector("#customers").append(tr);

  });
}


// calling  api for cart data after adding product to cart
window.addEventListener("load", async () => {

  const cartData = await getCartData();
  appendData(cartData);
  console.log(cartData);

});


// giving order 
// /customer assets/checkout/checkout.html

function orderNowFun() {


  fetch(`http://localhost:8888/orderController/giveyourorder/${customerId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({})
  })
  .then(response => response.json())
  .then(data => {
    // Handle the response data
    console.log(data);
    localStorage.setItem('order_id', data.orderId);
    alert("Order placed successfully! Please give yoru payment ")
    window.location.href = "../checkout/checkout.html";

  })
  .catch(error => {
    // Handle errors
    console.error(error);
  });

}

