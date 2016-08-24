package com.pubnub.braindrain;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.pubnub.braindrain.app.model.Board;
import com.pubnub.braindrain.app.model.Layer;
import com.pubnub.braindrain.app.model.Point;


public class PayloadSerializeTest {


    @Test
    public void testInflate(){
        Board board = new Board();
        board.setBoardName("test");

        Layer layer = new Layer();

        for(int x=0;x <= 10; ++x){
            Point p = new Point(x,10);
            layer.getPoints().add(p);
        }

        board.getLayers().add(layer);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writer().writeValueAsString(board);
            assertNotNull("the serialization should have produced json" ,result);
        }catch(Exception w){
            fail("exception while serializing JSON");
        }
    }

}
