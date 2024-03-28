/* eslint-disable react/prop-types */
// import PropTypes from "prop-types";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import Divider from "@mui/material/Divider";
import Drawer from "@mui/material/Drawer";
import IconButton from "@mui/material/IconButton";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import MenuIcon from "@mui/icons-material/Menu";
import BarChartIcon from "@mui/icons-material/BarChart";
import QueryStatsIcon from "@mui/icons-material/QueryStats";
import PostAddIcon from "@mui/icons-material/PostAdd";
import PortraitIcon from "@mui/icons-material/Portrait";
import LogoutIcon from "@mui/icons-material/Logout";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";

import FDMLogoBlack from "../assets/fdm-logo-black.svg";
import { useState } from "react";
import { Link } from "react-router-dom";
import { navBarItemsAdmin, navBarItemsUser } from "../Data";
import { NavLink } from "react-router-dom";

const drawerWidth = 240;

const NavBar = () => {
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  const [mobileOpen, setMobileOpen] = useState(false);
  const [isClosing, setIsClosing] = useState(false);

  const handleDrawerClose = () => {
    setIsClosing(true);
    setMobileOpen(false);
  };

  const handleDrawerTransitionEnd = () => {
    setIsClosing(false);
  };

  const handleDrawerToggle = () => {
    if (!isClosing) {
      setMobileOpen(!mobileOpen);
    }
  };

  const drawer = (
    <div>
      <Toolbar>
        <img src={FDMLogoBlack} alt="FDM-logo" style={{ width: "3rem" }} />
      </Toolbar>
      <Divider />
      <List>
        {loggedInUser.role === "admin"
          ? navBarItemsAdmin.map((item, index) => (
              <ListItem key={item.name} disablePadding>
                <NavLink
                  to={item.path}
                  style={({ isActive }) => ({
                    color: isActive ? "#1976d2" : "#858585",
                  })}
                >
                  <ListItemButton>
                    <ListItemIcon>
                      {index === 0 && <BarChartIcon />}
                      {index === 1 && <QueryStatsIcon />}
                      {index === 2 && <PostAddIcon />}
                      {index === 3 && <PortraitIcon />}
                      {index === 4 && <LogoutIcon />}
                    </ListItemIcon>
                    <ListItemText primary={item.name} />
                  </ListItemButton>
                </NavLink>
              </ListItem>
            ))
          : navBarItemsUser.map((item, index) => (
              <ListItem key={item.name} disablePadding>
                <Link to={item.path}>
                  <ListItemButton>
                    <ListItemIcon>
                      {index === 0 && <BarChartIcon />}
                      {index === 1 && <QueryStatsIcon />}
                      {index === 2 && <PortraitIcon />}
                      {index === 3 && <LogoutIcon />}
                    </ListItemIcon>
                    <ListItemText primary={item.name} />
                  </ListItemButton>
                </Link>
              </ListItem>
            ))}
      </List>
    </div>
  );
  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` },
        }}
      >
        <Toolbar style={{ justifyContent: "space-between" }}>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: "none" } }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" noWrap component="div">
            Dash Board
          </Typography>
          {loggedInUser === "" ? (
            <Button color="inherit">Login</Button>
          ) : (
            <Button color="inherit">Hello, {loggedInUser.firstName}</Button>
          )}
        </Toolbar>
      </AppBar>
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="mailbox folders"
      >
        {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
        <Drawer
          // container={container}
          variant="temporary"
          open={mobileOpen}
          onTransitionEnd={handleDrawerTransitionEnd}
          onClose={handleDrawerClose}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { xs: "block", sm: "none" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
            },
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: "none", sm: "block" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
            },
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          width: { sm: `calc(100% - ${drawerWidth}px)` },
        }}
      ></Box>
    </Box>
  );
};

export default NavBar;
