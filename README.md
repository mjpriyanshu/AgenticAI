# Smart Home Energy Advisor

## Description
Smart AI agent that analyzes home energy use and gives tips to save power and reduce bills. Built with IBM watsonx.ai to help users understand and optimize their home electricity usage.

## Features
- Analyzes smart meter and appliance data
- Provides energy-saving recommendations
- Suggests optimal times to run appliances
- Answers queries about electricity bills and consumption
- Offers actionable tips to reduce energy waste and costs

## Prerequisites
- Java 8 or higher
- IBM Cloud account with watsonx.ai access
- Valid IBM Cloud API key
- Deployed watsonx.ai model

## Setup Instructions

### 1. Get IBM Cloud Credentials
1. Create an IBM Cloud account at [https://cloud.ibm.com/](https://cloud.ibm.com/)
2. Generate an API key from IBM Cloud dashboard
3. Deploy a watsonx.ai model and get the deployment URL

### 2. Configure the Application
1. Open `WatsonxClient.java`
2. Replace the following placeholders with your actual values:
   ```java
   String API_KEY = "YOUR_API_KEY_HERE";
   String DEPLOYMENT_URL = "YOUR_DEPLOYMENT_URL_HERE";
   ```

### 3. API Endpoints Used
- **IBM Cloud IAM Token Endpoint**: `https://iam.cloud.ibm.com/identity/token`
- **Watsonx.ai Deployment Endpoint**: Your specific deployment URL (format: `https://us-south.ml.cloud.ibm.com/ml/v4/deployments/{deployment_id}/...`)

## How to Run

### Compile the Java file:
```bash
javac WatsonxClient.java
```

### Run the application:
```bash
java WatsonxClient
```

## AI Agent Instructions

### Primary Role
You are a smart home energy advisor built with watsonx.ai. When greeted, say:
> **"Hi, I am your Smart Home Energy Advisor powered by watsonx.ai. How can I help you save energy today?"**

### Core Responsibilities
- Answer user queries about high electricity bills, appliance consumption, and energy-saving tips
- Suggest optimal times to run appliances based on usage patterns
- Provide simple, actionable energy recommendations in clear language
- Always be friendly, informative, and focused on helping users reduce energy waste and cost

### General Guidelines
You are a helpful, polite, and knowledgeable AI assistant. You understand user questions and respond clearly and concisely. When greeted, introduce yourself and offer assistance. Always provide accurate, friendly, and informative responses. If the user asks a follow-up, stay in context and guide them step by step.

## Example Usage
The application sends queries to the watsonx.ai model, such as:
- "Suggest ways to reduce my electricity bill"
- "What appliances use the most energy?"
- "When is the best time to run my washing machine?"

## Troubleshooting

### Common Issues
1. **HTTP 400 Error**: Check if your API key is valid and active
2. **HTTP 500 Error**: Verify your deployment URL is correct and the model is deployed
3. **Compilation Warnings**: The `URL(String)` constructor is deprecated in Java 20+, but still functional

### Error Handling
The application includes comprehensive error handling that will display:
- HTTP response codes
- Error messages from IBM Cloud
- Detailed error information for debugging

## Project Structure
```
├── WatsonxClient.java          # Main application file
├── README.md                   # This documentation
└── Images/                     # Screenshots and documentation images
```



## License
This project is for educational purposes as part of the IBM SkillsBuild program.

## Support
For issues related to:
- IBM Cloud: Check [IBM Cloud Documentation](https://cloud.ibm.com/docs)
- Watsonx.ai: Visit [IBM watsonx Documentation](https://www.ibm.com/docs/en/watsonx-as-a-service)