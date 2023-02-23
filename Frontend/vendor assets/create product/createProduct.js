document.querySelector("form").addEventListener("submit", createProduct);
const sessionKey = localStorage.getItem("sessionKey");

function createProduct(e) {
  e.preventDefault();

  let productName = document.getElementById("productname").value;

  let productUrl = document.getElementById("productimage").value;

  let productDescription = document.getElementById("discription").value;

  let productMarketPrice = document.getElementById("productprice").value;

  let productDiscount = document.getElementById("productDiscount").value;

  let productCategory = document.getElementById("category").value;

  let productQuantity = document.getElementById("quantity").value;

  // converting string to double & integer
  const productMarketPriceDouble = parseFloat(productMarketPrice);
  const productDiscountDouble = parseFloat(productDiscount);
  let quantity = parseInt(productQuantity);

  const listData = {
    productName: productName,
    picture: productUrl,
    quantity: quantity,
    marketPrice: productMarketPriceDouble,
    productDescription: productDescription,
    discount: productDiscountDouble,
    category: productCategory,
  };

  console.log(listData);

  try {
    sendProductData(listData);
    alert("Your product is listed Successfully");
    window.location.href = "../dashboard/dashboard.html";
    
  } catch (error) {
    console.error(error);
    alert("Error: " + error.message);
  }
}

let sendProductData = async (obj) => {
  try {
    const res = await fetch(
      `http://shopeasy-env.eba-xkxpqfpn.ap-south-1.elasticbeanstalk.com//vendorController/addproduct/${sessionKey}`,
      {
        method: "POST",
        body: JSON.stringify(obj),
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
