import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Dashboard from './components/Dashboard'
import Login from './components/Login'
import { BrowserRouter, Routes,Route } from 'react-router-dom'
import Main from './components/Main'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
      <Main />
    </BrowserRouter>
    </>
  )
}

export default App
