import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';

function Navbar() {
  return (
    <AppBar position="static">
      <Toolbar>
        <Container>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Store Front
          </Typography>
          <Button color="inherit" href="/home">Home</Button>
          <Button color="inherit" href="/about">About</Button>
          <Button color="inherit" href="/storePage">Services</Button>
          <Button color="inherit" href="/contact">Contact</Button>
        </Container>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;