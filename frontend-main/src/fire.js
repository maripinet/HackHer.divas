import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

export const firebaseConfig = {
  apiKey: "SuaApiKey",
  authDomain: "SeuAuthDomain",
  projectId: "SeuProjectId",
  storageBucket: "SeuStorageBucket",
  messagingSenderId: "SeuMessagingSenderId",
  appId: "SeuAppId"
};


const firebaseApp = initializeApp(firebaseConfig);


export const auth = getAuth(firebaseApp);