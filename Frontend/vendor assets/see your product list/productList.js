
// I am sellecting button and input 
const searchBtn = document.querySelector(".search");
const resetBtn = document.querySelector(".reset");
const searchInput = document.querySelector("#search-input");
const vendorId = localStorage.getItem("userId");

let allProducts = [];

async function findAllProduct() {
  try {
    let res = await fetch(`http://localhost:8888/vendorController/viewallproducts/${vendorId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (res.ok) {
      let data = await res.json();
      allProducts = data;
      appendData(data);
    } else {
      let data = await res.json();
      let error = JSON.stringify(data);
      let msg = JSON.parse(error);
      console.log(msg);
      alert(msg.message);
    }
  } catch (error) {
    console.log(error);
    alert("Connection failed");
  }
}

function searchProductById(id) {
  let foundProducts = allProducts.filter(product => product.productId == id);
  console.log(allProducts)
  if (foundProducts.length > 0) {
    clearTable();
    appendData(foundProducts);
  } else {
    alert("No product found with the specified id.");
  }
}

// I will clear all data that append already in td
function clearTable() {
    
    let rows = document.querySelectorAll("#customers tr");
    
    rows.forEach((row) => {
      row.querySelectorAll("td").forEach((td) => {
        td.remove();
      });
    });
  }
  

// creating function for appending all data
function appendData(data) {
  data.map(function (el) {
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

    let marketPrice = document.createElement("td");
    marketPrice.innerText = el.marketPrice;

    let AfterDiscountPrice = document.createElement("td");
    AfterDiscountPrice.innerText = el.afterDiscountPrice;

    let discription = document.createElement("td");
    discription.innerText = el.productDescription;

    let category_type = document.createElement("td");
    category_type.innerText = el.category;

    // in tr appending all td then appending to table
    tr.append(prid, prName, marketPrice, quantity, discount, discription, category_type, AfterDiscountPrice);
    document.querySelector("#customers").append(tr);
  });
}

// on window load I am calling function where I am  making get request
window.onload = findAllProduct;

searchBtn.addEventListener("click", function () {
  let id = searchInput.value;
  if (id) {
    searchProductById(id);
  } else {
    alert("Please enter a product id to search.");
  }

});

resetBtn.addEventListener("click", function () {
  clearTable();
  findAllProduct();
  searchInput.value = "";
});
