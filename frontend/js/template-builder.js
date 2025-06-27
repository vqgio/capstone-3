let templateBuilder = {};

class TemplateBuilder {
    build(templateName, value, target, callback) {
        axios.get(`templates/${templateName}.html`)
            .then(response => {
                const template = response.data;
                const html = Mustache.render(template, value);
                document.getElementById(target).innerHTML = html;
                if (typeof callback === 'function') callback();
            })
            .catch(error => console.error(`Error loading template: ${templateName}`, error));
    }

    clear(target) {
        const element = document.getElementById(target);
        if (element) element.innerHTML = "";
    }

    append(templateName, value, target) {
        axios.get(`templates/${templateName}.html`)
            .then(response => {
                const template = response.data;
                const html = Mustache.render(template, value);

                const element = this.createElementFromHTML(html);
                const parent = document.getElementById(target);
                parent.appendChild(element);

                if (target === "errors") {
                    setTimeout(() => {
                        parent.removeChild(element);
                    }, 3000);
                }
            })
            .catch(error => console.error(`Error appending template: ${templateName}`, error));
    }

    createElementFromHTML(htmlString) {
        const div = document.createElement('div');
        div.innerHTML = htmlString.trim();
        return div.firstChild;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    templateBuilder = new TemplateBuilder();
});

