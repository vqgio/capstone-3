


function loadCategories(categories) {
    const select = document.getElementById('category-select');

    // Clear previous options
    select.innerHTML = '';

    // Add default option
    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    defaultOption.textContent = 'Choose a category...';
    select.appendChild(defaultOption);

    // Populate categories
    categories.forEach(({ categoryId, name }) => {
        const option = document.createElement('option');
        option.value = categoryId;
        option.textContent = name;
        select.appendChild(option);
    });
}

