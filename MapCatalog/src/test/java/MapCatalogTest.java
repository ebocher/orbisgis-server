/**
 * OrbisGIS is a GIS application dedicated to scientific spatial simulation.
 * This cross-platform GIS is developed at French IRSTV institute and is able to
 * manipulate and create vector and raster spatial information.
 *
 * OrbisGIS is distributed under GPL 3 license. It is produced by the "Atelier
 * SIG" team of the IRSTV Institute <http://www.irstv.fr/> CNRS FR 2488.
 *
 * Copyright (C) 2007-2012 IRSTV (FR CNRS 2488)
 *
 * This file is part of OrbisGIS.
 *
 * OrbisGIS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * OrbisGIS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * OrbisGIS. If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, please consult: <http://www.orbisgis.org/> or contact
 * directly: info_at_ orbisgis.org
 */
import org.junit.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Test class for MapCatalog
 * @author Mario Jothy
 */
public class MapCatalogTest {

    @Test
    public void workspaceCreation () throws SQLException{

        //Creation of the workspace
        Long id_workspace = MapCatalog.createWorkspace(null, "bbb", 0);
        String query = "SELECT id_creator , name , isPublic FROM workspace WHERE id_workspace=" + id_workspace;
        ArrayList<String[]> value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                        value.get(0)[0] == null &&
                        value.get(0)[1].equals("bbb") &&
                        value.get(0)[2].equals("0")
        );
        //Deletion of the workspace
        MapCatalog.deleteWorkspace(id_workspace);
        query = "SELECT name FROM workspace WHERE id_workspace="+id_workspace;
        value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                        value.isEmpty()
        );
    }

    @Test
    public void folderCreation () throws SQLException{
        //Creation of the folder
        Long id_folder = MapCatalog.createFolder(new Long(2), null, "aaa");
        String query = "SELECT id_root , id_parent , name FROM folder WHERE id_folder=" + id_folder;
        ArrayList<String[]> value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                        value.get(0)[0].equals("2")    &&
                        value.get(0)[1] == null        &&
                        value.get(0)[2].equals("aaa")
        );
        //Deletion of the folder
        MapCatalog.deleteFolder(id_folder);
        query = "SELECT name FROM folder WHERE id_folder="+id_folder;
        value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                        value.isEmpty()
        );
    }

    @Test
    public void userCreation() throws SQLException{
        //Creation of the user
        Long id_user = MapCatalog.createUser("moi", "moi@moi.moi", "aaa", "paris");
        String query = "SELECT name , email , password , location FROM user WHERE id_user=" + id_user;
        ArrayList<String[]> value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                        value.get(0)[0].equals("moi")           &&
                        value.get(0)[1].equals("moi@moi.moi")   &&
                        value.get(0)[2].equals("aaa")           &&
                        value.get(0)[3].equals("paris")
        );
        //Deletion of the user
        MapCatalog.deleteUser(id_user);
        query = "SELECT name FROM user WHERE id_user="+id_user;
        value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                value.isEmpty()
        );
    }

    @Test
    public void commentCreation() throws SQLException{
        //Creation of the comment
        Long id_comment = MapCatalog.createComment(null, null, "a content", "default");
        String query = "SELECT id_writer , id_map , content , title FROM comment WHERE id_comment=" + id_comment;
        ArrayList<String[]> value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                        value.get(0)[0]==null               &&
                        value.get(0)[1]==null               &&
                        value.get(0)[2].equals("a content") &&
                        value.get(0)[3].equals("default")
        );
        //Deletion of the comment
        MapCatalog.deleteComment(id_comment);
        query = "SELECT content FROM comment WHERE id_comment="+id_comment;
        value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                value.isEmpty()
        );
    }

    @Test
    public void owsCreation() throws SQLException{
        //Creation of the ows
        Long id_owscontext = MapCatalog.createOWS(new Long(2), null, null, "a content");
        String query = "SELECT id_root , id_parent , id_uploader , content FROM owscontext WHERE id_owscontext=" + id_owscontext;
        ArrayList<String[]> value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                        value.get(0)[0].equals("2")     &&
                        value.get(0)[1]==null           &&
                        value.get(0)[2]==null           &&
                        value.get(0)[3].equals("a content")
        );
        //Deletion of the owscontext
        MapCatalog.deleteOWSContext(id_owscontext);
        query = "SELECT content FROM owscontext WHERE id_owscontext="+id_owscontext;
        value = MapCatalog.executeSQLselect(MapCatalog.getConnection(), query);
        Assert.assertTrue(
                value.isEmpty()
        );
    }

}