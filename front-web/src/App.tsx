import { useEffect } from 'react';
import './App.css';
import Navbar from './Navbar'

function App() {

  useEffect(() => {
    //chamada para API para buscar produtos
  }, [])
  return (
    <div>
     <Navbar />
    </div>
  );
}

export default App;
