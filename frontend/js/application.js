
// Show login modal
function showLoginForm() {
    templateBuilder.build('login-form', {}, 'login');
}

// Hide login modal
function hideModalForm() {
    templateBuilder.clear('login');
}

// Attempt user login
function login() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;

    if (!username || !password) {
        showError("Username and password are required.");
        return;
    }

    userService.login(username, password);
    hideModalForm();
}

// Show product image in a modal
function showImageDetailForm(product, imageUrl) {
    templateBuilder.build('image-detail', { name: product, imageUrl }, 'login');
}

// Load home screen
function loadHome() {
    templateBuilder.build('home', {}, 'main');
    productService.search();
    categoryService.getAllCategories(loadCategories);
}

// Profile handling
function editProfile() {
    profileService.loadProfile();
}

function saveProfile() {
    const profile = {
        firstName: getValue("firstName"),
        lastName: getValue("lastName"),
        phone: getValue("phone"),
        email: getValue("email"),
        address: getValue("address"),
        city: getValue("city"),
        state: getValue("state"),
        zip: getValue("zip")
    };

    profileService.updateProfile(profile);
}

// Cart handling
function showCart() {
    cartService.loadCartPage();
}

function clearCart() {
    cartService.clearCart();
    cartService.loadCartPage();
}

// Filter handling
function setCategory(control) {
    productService.addCategoryFilter(control.value);
    productService.search();
}

function setColor(control) {
    productService.addColorFilter(control.value);
    productService.search();
}

function setMinPrice(control) {
    const label = document.getElementById("min-price-display");
    label.innerText = control.value;

    const value = control.value !== "0" ? control.value : "";
    productService.addMinPriceFilter(value);
    productService.search();
}

function setMaxPrice(control) {
    const label = document.getElementById("max-price-display");
    label.innerText = control.value;

    const value = control.value !== "1500" ? control.value : "";
    productService.addMaxPriceFilter(value);
    productService.search();
}

// Temporary error close
function closeError(control) {
    setTimeout(() => control.click(), 3000);
}

// Utility: Get trimmed input value
function getValue(id) {
    return document.getElementById(id).value.trim();
}

// Optional: Simple error message helper
function showError(message) {
    const errorContainer = document.getElementById("errors");
    errorContainer.innerHTML = `
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>`;
}

document.addEventListener('DOMContentLoaded', loadHome);
