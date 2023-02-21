//  get vendor profile- get request

const sessionKey = localStorage.getItem("sessionKey");
console.log(sessionKey);

let vendorLoggedin = async (obj) => {
    try {
        const res = await fetch(
            `http://localhost:8888/vendorController/singalvendor/${sessionKey}`,
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
    // selecting from html and after that appending into it
    const Imgcontainer = document.querySelector(".profile__img--container");
    const profileContainer = document.querySelector(".profile__info");

    // creating img tag
    const img = document.createElement("img");
    img.src = data.personalInfo.imageUrl;

    // creating form tag, input tag and label 
    const form = document.createElement("form");

    const allLabelName = ["Name", "Phone", "Email", "Password", "Country", "State", "City", "Pincode"];
    const inputs = [];
    const values = [data.venderName, data.personalInfo.phone, data.personalInfo.email, data.personalInfo.password, data.personalInfo.country, data.personalInfo.state, data.personalInfo.city, data.personalInfo.pincode,];

    for (let i = 0; i < allLabelName.length; i++) {
        const label = document.createElement("label");
        label.innerHTML = allLabelName[i];

        const input = document.createElement("input");

        input.name = allLabelName[i];
        input.value = values[i];
        inputs.push(input);
        form.appendChild(label);
        form.appendChild(input);
    }

    // appending input to form to profileContainer& img to imgcontainer
    Imgcontainer.append(img);
    profileContainer.append(form);
};

// calling  api for vendor data after login
window.addEventListener("load", async () => {
    const vendorData = await vendorLoggedin();
    appendData(vendorData);
});