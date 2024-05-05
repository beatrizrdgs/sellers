import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import { useState, useEffect } from 'react';
import Button from '@mui/material/Button';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';


export default function Seller() {
    const paperStyle = { padding: '50px 20px', width: 600, margin: "2  0px auto" }
    const[name, setName]=useState('')
    const[gender, setGender]=useState('')
    const[seller, setSeller]=useState([])
    const handleSubmit = (e) => {
        e.preventDefault();
        const normalizedGender = gender === "other" ? null : gender;
        const newSeller = { name, gender: normalizedGender };
        fetch("http://localhost:8080/sellers", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newSeller),
        }).then(() => {
            console.log("New seller added");
            setName("");
            setGender("");
            window.location.reload();
            alert("New seller added");
        })
    }

    const handleDelete = (id) => {
        fetch(`http://localhost:8080/sellers/${id}`, {
            method: "DELETE",
        }).then(() => {
            console.log("Sellers deleted");
            window.location.reload();
            alert("Sellers deleted");
        })            
    }

    const handleRandomSeller = () => {
        fetch(`http://localhost:8080/sellers/random`, {
            method: "POST",
        }).then(() => {
            console.log("Random seller added");
            alert("Random seller added");
            window.location.reload();
        })
    }

    useEffect(() => {
        fetch("http://localhost:8080/sellers")
        .then((response) => response.json()).then((data) => setSeller(data));
    })

    return (
    <Container style={{ marginTop: '2rem' }} align="center">
        <Paper elevation={3} style={paperStyle}>
            <h1>Add Seller</h1>
    <Box
      component="form"
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >

      <TextField id="standard-basic" label="Name" variant="standard"
      value={name}
      onChange={(e)=>setName(e.target.value)}
      />

      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Gender</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={gender}
          label="Gender"
          onChange={(e)=>setGender(e.target.value)}
        >
          <MenuItem value={"male"}>Male</MenuItem>
          <MenuItem value={"female"}>Female</MenuItem>
          <MenuItem value={"other"}>Other</MenuItem>
        </Select>
      </FormControl>

      <Button variant="contained" onClick={handleSubmit}>Submit</Button>

      <Button variant="contained" onClick={handleRandomSeller}>Generate Random Seller</Button>

    </Box>
    </Paper>
    <h1>Sellers</h1>
    <Paper elevation={3} style={paperStyle}>
        {seller.map((seller) => (
            <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left"}} key={seller.id}>
                ID: {seller.id} <br/>
                Name: {seller.name} <br/>
                Gender: {seller.gender === null ? "other" : seller.gender} <br/>
                <Button variant="contained" color="error" onClick={() => handleDelete(seller.id)}>Delete</Button>
            </Paper>
        ))}
    </Paper>

    </Container>
  );
}
