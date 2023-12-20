import * as React from "react";
import {useEffect, useState} from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import {UUID} from "node:crypto";
import Divider from "@mui/material/Divider";
import Grid from "@mui/material/Grid";

interface Room {
  id: UUID;
  name: string;
  floor: number;
}

export default function BasicTable() {
  const [rooms, setRooms] = useState<Room[]>([]);
  const [roomName, setRoomName] = useState('');
  const [roomFloor, setRoomFloor] = useState('');
  const [isValid, setIsValid] = useState(true);

  const fetchRooms = () => {
    fetch("/room")
      .then((response) => response.json())
      .then((data) => setRooms(data));
  };

  useEffect(fetchRooms, []);

  const generateRoom = () => {
    if (roomName.length <= 10 && !isNaN(Number(roomFloor)) && roomFloor !== '') {
      fetch('/room', {
        method: 'POST',
        body: JSON.stringify({name: roomName, floor: Number(roomFloor)}),
        headers: [["Content-Type", "application/json"]]
      }).then(() => {
        setRoomName('');
        setRoomFloor('');
        fetchRooms();
        setIsValid(true);
      });
    } else {
      setIsValid(false);
    }
  };

  return (
    <React.Fragment>
      <TableContainer component={Paper}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell align="left">Id</TableCell>
              <TableCell align="left">Name</TableCell>
              <TableCell align="left">Floor</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rooms.map((room) => (
              <TableRow
                key={room.id}
                sx={{"&:last-child td, &:last-child th": {border: 0}}}
              >
                <TableCell component="th" scope="row">
                  {room.id}
                </TableCell>
                <TableCell align="left">{room.name}</TableCell>
                <TableCell align="left">{room.floor}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <Divider light/>
      <Paper sx={{padding: '1em'}}>

        <Grid container spacing={1} alignItems="center">
          <Grid item>
            <TextField error={!isValid}
                       helperText={!isValid && "Name should be less than 10 characters and floor should be a number"}
                       label="Room Name" value={roomName} onChange={(e) => setRoomName(e.target.value)}/>
          </Grid>
          <Grid item>
            <TextField error={!isValid}
                       helperText={!isValid && "Name should be less than 10 characters and floor should be a number"}
                       label="Floor Number" value={roomFloor} onChange={(e) => setRoomFloor(e.target.value)}/>
          </Grid>
          <Grid item>
            <Button variant="contained" onClick={generateRoom}>Generate New Room</Button>
          </Grid>
        </Grid>
      </Paper>
    </React.Fragment>
  );
}