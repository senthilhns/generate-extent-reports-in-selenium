class UniqueSignUpFormElement extends HTMLElement {

    constructor() {
        super();
        this.attachShadow({ mode: "open" });
    }

    connectedCallback() {
        const title = this.getAttribute('title');
        const termsOfUse = this.getAttribute('terms');
        const privacyPolicy = this.getAttribute('policy');
        const buttonName = this.getAttribute('button-name');
        const color = this.getAttribute('color');

        const css = `
            <style>
            body {
                color: #999;
                background: #f3f3f3;
                font-family: 'Roboto', sans-serif;
            }
            .form-control {
                border-color: #eee;
                min-height: 41px;
                box-shadow: none !important;
            }
            .form-control:focus {
                border-color: ${color};
            }
            .form-control, .btn {        
                border-radius: 3px;
            }
            .signup-form {
                width: 500px;
                margin: 0 auto;
                padding: 30px 0;
            }
            .signup-form h2 {
                color: #333;
                margin: 0 0 30px 0;
                display: inline-block;
                padding: 0 30px 10px 0;
                border-bottom: 3px solid ${color};
            }
            .signup-form form {
                color: #999;
                border-radius: 3px;
                margin-bottom: 15px;
                background: #fff;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .signup-form .form-group row {
                margin-bottom: 20px;
            }
            .signup-form label {
                font-weight: normal;
                font-size: 14px;
                line-height: 2;
            }
            .signup-form input[type="checkbox"] {
                position: relative;
                top: 1px;
            }
            .signup-form .btn {        
                font-size: 16px;
                font-weight: bold;
                background: ${color};
                border: none;
                margin-top: 20px;
                min-width: 140px;
            }
            .signup-form .btn:hover, .signup-form .btn:focus {
                background: #41cba9;
                outline: none !important;
            }
            .signup-form a {
                color: ${color};
                text-decoration: underline;
            }
            .signup-form a:hover {
                text-decoration: none;
            }
            .signup-form form a {
                color: ${color};
                text-decoration: none;
            }	
            .signup-form form a:hover {
                text-decoration: underline;
            }
            </style>`;

        this.shadowRoot.innerHTML += `
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        ${css}
        <div class="signup-form">
        <p><h2 name="headline">${title}</h2></p>
            <form action="/examples/actions/confirmation.php" method="post" class="form-horizontal">
                <div class="row">
                    <div class="col-8 offset-4">
                        <h2>Sign Up</h2>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-4">Username</label>
                    <div class="col-8">
                        <input type="text" class="form-control" name="username" required="required">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-4">Email Address</label>
                    <div class="col-8">
                        <input type="email" class="form-control" name="email" required="required">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-4">Password</label>
                    <div class="col-8">
                        <input type="password" class="form-control" name="password" required="required">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-4">Confirm Password</label>
                    <div class="col-8">
                        <input type="password" class="form-control" name="confirm_password" required="required">
                    </div>
                </div>
                <div class="form-group row">
                <div class="col-8 offset-4">
                    <p><label class="form-check-label"><input type="checkbox" required="required"> I accept the <a href="${termsOfUse}">Terms of Use</a> &amp; <a href="${privacyPolicy}">Privacy Policy</a>.</label></p>
        
                    <button type="submit" class="btn btn-primary btn-lg">${buttonName}</button>
                </div>  
            </div>		      
            </form>
            <div class="text-center">Already have an account? <a href="#">Login here</a></div>
            </div>`;
    }
}

// CustomElementRegistry object - information on what custom elements are registered
customElements.define("unique-signup-form", UniqueSignUpFormElement);