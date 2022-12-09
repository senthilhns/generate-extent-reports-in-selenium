class UniqueSignUpFormElement extends HTMLElement {

    constructor() {
        super();
    }

    connectedCallback() {
        const title = this.getAttribute('title');
        const termsOfUse = this.getAttribute('terms');
        const privacyPolicy = this.getAttribute('policy');
        const buttonName = this.getAttribute('button-name');
        const color = this.getAttribute('color');

       
        const template = document.getElementById("signup-form-template").content;
        const shadowRoot = this.attachShadow({ mode: "open" });

        shadowRoot.appendChild(template.cloneNode(true));
        shadowRoot.getElementById('headline').textContent = title;
        shadowRoot.getElementById('registed-button').textContent = buttonName;
        shadowRoot.getElementById('privacy-policy').href = privacyPolicy;
        shadowRoot.getElementById('terms-use').href = termsOfUse;

        var sheet = new CSSStyleSheet();
        sheet.replaceSync( 
        `.form-control:focus {
                border-color: ${color};
            }
            .signup-form h2 {
                border-bottom: 3px solid ${color};
            }       
            .signup-form .btn {    
                background: ${color};
            
            }
            .signup-form a {
                color: ${color};
            }
            .signup-form form a {
                color: ${color};
            }`);
        this.shadowRoot.adoptedStyleSheets = [ sheet ];
    }
}

customElements.define("unique-signup-form", UniqueSignUpFormElement);