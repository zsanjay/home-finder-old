const BASE_URL = process.env.NEXT_PUBLIC_HOME_FINDER_API_URL;

const SignUpFn = async (userData) => {
    try {
        console.log("signup props ", userData);
        console.log("BASE_URL ", BASE_URL);

        if (!BASE_URL) {
            throw new Error("API URL is not configured. Please check your environment variables.");
        }

        const response = await fetch(`${BASE_URL}/auth/signup`, {
            method: 'POST',
            body: JSON.stringify(userData),
            headers: {
                'Content-Type': 'application/json',
            }
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || "Failed to Sign Up");
        }

        return data;
    } catch (error) {
        console.error("SignUp Error:", error);
        throw error;
    }
};

const SignInFn = async (userData) => {
    console.log("signin props ", userData);
    
    const response  = await fetch(`${BASE_URL}/auth/login`, {
        method : 'POST',
        body : JSON.stringify(userData),
        headers : {
            'Content-Type' : 'application/json',
        }
    });

    if(!response.ok) {
        throw new Error("Failed to Sign In");
    }

    return response.json();
} 

export { SignUpFn , SignInFn };
